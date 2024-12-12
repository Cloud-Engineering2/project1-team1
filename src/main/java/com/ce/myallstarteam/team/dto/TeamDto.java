package com.ce.myallstarteam.team.dto;


import com.ce.myallstarteam.player.dto.PlayerDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class TeamDto {
    private int teamId;
    private int userId;
    private List<PlayerDto> players;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
