package com.ce.myallstarteam.player.dto.request;

import com.ce.myallstarteam.player.dto.PlayerDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlayerRequest {
    private int id;
    private String position;

    public static PlayerDto toDto(PlayerRequest playerRequest, String name) {
        return new PlayerDto(
                playerRequest.getId(),
                playerRequest.getPosition(),
                name
        );
    }
}
