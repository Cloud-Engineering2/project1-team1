package com.ce.myallstarteam.team.dto;

import com.ce.myallstarteam.player.dto.PlayerDto;
import com.ce.myallstarteam.player.entity.Player;
import com.ce.myallstarteam.team.entity.Team;
import com.ce.myallstarteam.team.entity.TeamPlayer;
import com.ce.myallstarteam.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TeamPlayerDto {

    private final Integer id;          // TeamPlayer ID
    private final String position; // 포지션
    private final Integer playerId;    // Player ID
    private final String playerName; // Player 이름

    // TeamPlayer 엔티티에서 DTO로 변환하는 메서드
    public static TeamPlayerDto fromEntity(TeamPlayer teamPlayer) {
        Player player = teamPlayer.getPlayer(); // Player 객체 가져오기
        return TeamPlayerDto.builder()
                .id(teamPlayer.getId())
                .position(teamPlayer.getPosition())
                .playerId(player.getId())
                .playerName(player.getName())
                .build();
    }
}