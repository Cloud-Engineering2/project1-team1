package com.ce.myallstarteam.player.dto;

import com.ce.myallstarteam.player.entity.Player;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PlayerDto {
    private Integer id;
    private String name;
    private String teamName;
    private String dPosition;
    private Integer dErrors;
    private Double dFieldingPercentage;
    private Double dCsp;
    private Double hBattingAvg;
    private Integer hRuns;
    private Integer hHits;
    private Integer hHomeRuns;
    private Double pEra;
    private Integer pWins;
    private Integer pSaves;
    private Integer pStrikeOuts;

    public static PlayerDto fromEntity(Player player) {
        return PlayerDto.builder()
                .id(player.getId())
                .name(player.getName())
                .teamName(player.getTeamName())
                .dPosition(player.getDPosition())
                .dErrors(player.getDErrors())
                .dFieldingPercentage(player.getDFieldingPercentage())
                .dCsp(player.getDCsp())
                .hBattingAvg(player.getHBattingAvg())
                .hRuns(player.getHRuns())
                .hHits(player.getHHits())
                .hHomeRuns(player.getHHomeRuns())
                .pEra(player.getPEra())
                .pWins(player.getPWins())
                .pSaves(player.getPSaves())
                .pStrikeOuts(player.getPStrikeOuts())
                .build();
    }
}
