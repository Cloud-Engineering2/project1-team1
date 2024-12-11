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
public class DefenseDto {
	private String name;
	private String teamName;
	private String position;
	private int errors;
	private double fieldingPercentage;
	private double csp;
	
    public static DefenseDto fromDefenseInfo(List<WebElement> info) {
    	String fpct = info.get(12).getText().equals("-") ? "0" : info.get(12).getText();
    	String csp = info.get(16).getText().equals("-") ? "0" : info.get(12).getText();
    	
    	return DefenseDto.builder()
    			.name(info.get(1).getText())
    			.teamName(info.get(2).getText())
    			.position(info.get(3).getText())
    			.errors(Integer.parseInt(info.get(7).getText()))
    			.fieldingPercentage(Double.parseDouble(fpct))
    			.csp(Double.parseDouble(csp))
    			.build();
    }
}
