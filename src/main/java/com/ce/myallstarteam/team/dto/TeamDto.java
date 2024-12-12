package com.ce.myallstarteam.team.dto;

import com.ce.myallstarteam.team.entity.Team;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeamDto {

    private final int id;
    private final String teamName;
    private final int userId;

    public static TeamDto fromEntity(Team team) {
        return TeamDto.builder()
                .id(team.getId())
                .teamName(team.getName())
                .userId(team.getUser().getId()) // 사용자 ID 설정
                .build();
    }
}
