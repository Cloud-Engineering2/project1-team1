package com.ce.myallstarteam.player.dto;

import java.util.List;

import org.openqa.selenium.WebElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PitcherDto {
	private String name;
	private String teamName;
	private double era;
	private int wins;
	private int saves;
	private double winningPercentage;
	private int hitsAllows;
	private int hra;
	private int strikeOuts;
	
    public static PitcherDto fromPitcherInfo(List<WebElement> info) {
    	return PitcherDto.builder()
    			.name(info.get(1).getText())
    			.teamName(info.get(2).getText())
    			.era(Double.parseDouble(info.get(3).getText()))
    			.wins(Integer.parseInt(info.get(5).getText()))
    			.saves(Integer.parseInt(info.get(7).getText()))
    			.winningPercentage(Double.parseDouble(info.get(9).getText()))
    			.hitsAllows(Integer.parseInt(info.get(11).getText()))
    			.hra(Integer.parseInt(info.get(12).getText()))
    			.strikeOuts(Integer.parseInt(info.get(15).getText()))
    			.build();
    }
    
}
