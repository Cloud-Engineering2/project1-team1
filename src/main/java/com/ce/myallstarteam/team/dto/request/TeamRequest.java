package com.ce.myallstarteam.team.dto.request;

import com.ce.myallstarteam.player.dto.request.PlayerRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TeamRequest {
    private int teamId;
    private int userId;
    List<PlayerRequest> teamPlayers;
}
