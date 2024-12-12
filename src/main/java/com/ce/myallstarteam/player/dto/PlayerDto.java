package com.ce.myallstarteam.player.dto;

import com.ce.myallstarteam.player.entity.Player;
import com.ce.myallstarteam.player.entity.PlayerPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlayerDto {
    private int id;
    private String name;
    private String position;

    public static PlayerDto from(Player player) {
        return new PlayerDto(
                player.getId(),
                player.getName(),
                player.getDPosition()
        );
    }
}
