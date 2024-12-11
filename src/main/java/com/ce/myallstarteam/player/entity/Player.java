package com.ce.myallstarteam.player.entity;

import com.ce.myallstarteam.player.dto.DefenseDto;
import com.ce.myallstarteam.player.dto.HitterDto;
import com.ce.myallstarteam.player.dto.PitcherDto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    private int id;

    @Column(name = "p_name", length = 100)
    private String name;

    @Column(name = "p_team", length = 50)
    private String teamName;

    @Column(length = 30)
    private String dPosition;

    private int dErrors;

    private double dFieldingPercentage;

    private double dCsp;

    private double hBattingAvg;

    private int hRuns;

    private int hHits;

    private int hHomeRuns;

    private double pEra;

    private int pWins;

    private int pSaves;

    private double pWinningPercentage;

    private int pHitsAllows;

    private int pHra;

    private int pStrikeOuts;

    public static Player fromDefenseDto(DefenseDto dto) {
    	return Player.builder()
    			.name(dto.getName())
    			.teamName(dto.getTeamName())
    			.dPosition(dto.getPosition())
    			.dErrors(dto.getErrors())
    			.dFieldingPercentage(dto.getFieldingPercentage())
    			.dCsp(dto.getCsp())
    			.build();
    }
    
	public static Player fromPitcherDto(PitcherDto dto) {
		return Player.builder()
				.name(dto.getName())
				.teamName(dto.getTeamName())
				.pEra(dto.getEra())
				.pWins(dto.getWins())
				.pSaves(dto.getSaves())
				.pWinningPercentage(dto.getWinningPercentage())
				.pHitsAllows(dto.getHitsAllows())
				.pHra(dto.getHra())
				.pStrikeOuts(dto.getStrikeOuts())
				.build();
	}

	public static Player fromHitterDto(HitterDto dto) {
		return Player.builder()
				.name(dto.getName())
				.teamName(dto.getTeamName())
				.hBattingAvg(dto.getBattingAvg())
				.hRuns(dto.getRuns())
				.hHits(dto.getHits())
				.hHomeRuns(dto.getHomeRuns())
				.build();
	}
	
	public void updateDefenseField(DefenseDto dto) {
		name = dto.getName();
		teamName = dto.getTeamName();
		dPosition = dto.getPosition();
		dErrors = dto.getErrors();
		dFieldingPercentage = dto.getFieldingPercentage();
		dCsp = dto.getCsp();
	}

	public void updatePitcherField(PitcherDto dto) {
		name = dto.getName();
		teamName = dto.getTeamName();
		pEra = dto.getEra();
		pWins = dto.getWins();
		pSaves =dto.getSaves();
		pWinningPercentage = dto.getWinningPercentage();
		pHitsAllows = dto.getHitsAllows();
		pHra = dto.getHra();
		pStrikeOuts = dto.getStrikeOuts();
	}

	public void updateHitterField(HitterDto dto) {
		name = dto.getName();
		teamName = dto.getTeamName();
		hBattingAvg = dto.getBattingAvg();
		hRuns = dto.getRuns();
		hHits = dto.getHits();
		hHomeRuns = dto.getHomeRuns();
	}
}
