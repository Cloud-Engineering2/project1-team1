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
public class HitterDto {
	private String name;
	private String teamName;
	private double battingAvg;
	private int runs;
	private int hits;
	private int homeRuns;
	
    public static HitterDto fromHitterInfo(List<WebElement> info) {
    	return HitterDto.builder()
    			.name(info.get(1).getText())
    			.teamName(info.get(2).getText())
    			.battingAvg(Double.parseDouble(info.get(3).getText()))
    			.runs(Integer.parseInt(info.get(7).getText()))
    			.hits(Integer.parseInt(info.get(8).getText()))
    			.homeRuns(Integer.parseInt(info.get(11).getText()))
    			.build();
    }
}
