package com.ce.myallstarteam.team.dto;

import com.ce.myallstarteam.team.entity.Team;
import com.ce.myallstarteam.team.entity.TeamPlayer;
import com.ce.myallstarteam.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class TeamDto {

    private final Integer id;
    private final String teamName;
    private final Integer userId;
    private final List<TeamPlayerDto> teamPlayers;

    public static TeamDto fromEntity(Team team) {
        return TeamDto.builder()
                .id(team.getId())
                .teamName(team.getName())
                .userId(team.getUser().getId()) // 사용자 ID 설정
                .teamPlayers(team.getTeamPlayers().stream().map(TeamPlayerDto::fromEntity).toList())
                .build();
    }

    public Team toEntity(UserDto userDto) {
        return Team.of(userDto.toEntity(), teamName);
    }

}
