package com.ce.myallstarteam.team.service;

import com.ce.myallstarteam.player.dto.PlayerDto;
import com.ce.myallstarteam.team.repository.TeamPlayerRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TeamPlayerService {

    private final TeamPlayerRepository teamPlayerRepository;

    public TeamPlayerService(TeamPlayerRepository teamPlayerRepository) {
        this.teamPlayerRepository = teamPlayerRepository;
    }

    public Map<String, PlayerDto> getTeamPlayersByTeamId(int teamId) {
        Map<String, PlayerDto> positions = new HashMap<>();

        teamPlayerRepository.findByTeamId(teamId).forEach(teamPlayer -> {
            positions.put(teamPlayer.getPosition(),
                    PlayerDto.fromEntity(teamPlayer.getPlayer())); // DTO로 변환
        });

        String[] allPositions = {"중견수", "우익수", "좌익수", "유격수", "2루수", "3루수", "1루수", "투수", "포수"};
        for (String position : allPositions) {
            positions.putIfAbsent(position, null);
        }

        return positions;
    }
}