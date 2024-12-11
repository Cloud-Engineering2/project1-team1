package com.ce.myallstarteam.player.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ce.myallstarteam.player.dto.DefenseDto;
import com.ce.myallstarteam.player.dto.HitterDto;
import com.ce.myallstarteam.player.dto.PitcherDto;
import com.ce.myallstarteam.player.entity.Player;
import com.ce.myallstarteam.player.repository.PlayerRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlayerService {
	private final PlayerRepository playerRepository;
	
	@Transactional
    public void dataInit() {
        WebDriver driver = new ChromeDriver();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
        	List<HitterDto> hitterDtos = hitterInit(driver, wait);
        	List<PitcherDto> pitcherDtos = pitcherInit(driver, wait);
        	List<DefenseDto> defenseDtos = defenseInit(driver, wait);
        	System.out.println(hitterDtos.size()+" "+ pitcherDtos.size()+" "+defenseDtos.size());
        	
        	Map<String, Player> map = new HashMap<>();
        	addHitter(hitterDtos, map);
        	addPitcher(pitcherDtos, map);
        	addDefense(defenseDtos, map);
        	
        	playerRepository.saveAll(map.values());
        }finally {
			if(driver!=null) driver.quit();
		}
    }

	private void addDefense(List<DefenseDto> defenseDtos, Map<String, Player> map) {
		defenseDtos.stream().forEach(dto -> {
			if(map.containsKey(dto.getName())){
				if(map.get(dto.getName()).getTeamName().equals(dto.getTeamName())) {
					map.get(dto.getName()).updateDefenseField(dto);
				}
			}else {
				map.put(dto.getName(), Player.fromDefenseDto(dto));
			}
		});
	}
	private void addPitcher(List<PitcherDto> pitcherDtos, Map<String, Player> map) {
		pitcherDtos.stream().forEach(dto -> {
			if(map.containsKey(dto.getName())){
				if(map.get(dto.getName()).getTeamName().equals(dto.getTeamName())) {
					map.get(dto.getName()).updatePitcherField(dto);
				}
			}else {
				map.put(dto.getName(), Player.fromPitcherDto(dto));
			}
		});
	}
	private void addHitter(List<HitterDto> hitterDtos, Map<String, Player> map) {
		hitterDtos.stream().forEach(dto -> {
			if(map.containsKey(dto.getName())){
				if(map.get(dto.getName()).getTeamName().equals(dto.getTeamName())) {
					map.get(dto.getName()).updateHitterField(dto);
				}
			}else {
				map.put(dto.getName(), Player.fromHitterDto(dto));
			}
		});
	}

    private List<DefenseDto> defenseInit(WebDriver driver, Wait<WebDriver> wait) {
        driver.get("https://www.koreabaseball.com/Record/Player/Defense/Basic.aspx");
        List<DefenseDto> dtoList = new ArrayList<>();

        for (int i = 1 ; i < 31 ; i++){
            WebElement table = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#cphContents_cphContents_cphContents_udpContent > div.record_result > table > tbody")));
            List<WebElement> rows = table.findElements(By.tagName("tr"));
            rows.stream().forEach(row -> {
            	List<WebElement> info = row.findElements(By.tagName("td"));
            	dtoList.add(DefenseDto.fromDefenseInfo(info));
            });
            
            //next page
            int pageNum = i % 5;
            WebElement pageButton;
            if(i % 5 == 0) pageButton = driver.findElement(By.cssSelector("#cphContents_cphContents_cphContents_ucPager_btnNext > img"));
            else pageButton = driver.findElement(By.cssSelector("#cphContents_cphContents_cphContents_ucPager_btnNo"+(pageNum+1)));
            pageButton.click();
            wait.until(ExpectedConditions.stalenessOf(pageButton));
        }
        
        //마지막 페이지 추가
        WebElement table = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#cphContents_cphContents_cphContents_udpContent > div.record_result > table > tbody")));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        rows.stream().forEach(row -> {
        	List<WebElement> info = row.findElements(By.tagName("td"));
        	dtoList.add(DefenseDto.fromDefenseInfo(info));
        });
        
        return dtoList;
    }

    private List<PitcherDto> pitcherInit(WebDriver driver, Wait<WebDriver> wait) {
        driver.get("https://www.koreabaseball.com/Record/Player/PitcherBasic/Basic1.aspx");
        List<PitcherDto> dtoList = new ArrayList<>();
        List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#cphContents_cphContents_cphContents_udpContent > div.record_result > table > tbody > tr")));
        rows.stream().forEach(row -> {
        	List<WebElement> info = row.findElements(By.tagName("td"));
        	dtoList.add(PitcherDto.fromPitcherInfo(info));
        });
        return dtoList;
    }

    private List<HitterDto> hitterInit(WebDriver driver, Wait<WebDriver> wait) {
        driver.get("https://www.koreabaseball.com/Record/Player/HitterBasic/Basic1.aspx?sort=HRA_RT");
        List<HitterDto> dtoList = new ArrayList<>();
        // WebElement는 DOM 변경전에 반드시 처리해주어야함
        WebElement table = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#cphContents_cphContents_cphContents_udpContent > div.record_result > table > tbody")));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        rows.stream().forEach(row -> {
        	List<WebElement> info = row.findElements(By.tagName("td"));
        	dtoList.add(HitterDto.fromHitterInfo(info));
        });

        WebElement pageButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#cphContents_cphContents_cphContents_ucPager_btnNo2")));
        pageButton.click();

        wait.until(ExpectedConditions.stalenessOf(pageButton));

        table = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#cphContents_cphContents_cphContents_udpContent > div.record_result > table > tbody")));
        rows = table.findElements(By.tagName("tr"));
        rows.stream().forEach(row -> {
        	List<WebElement> info = row.findElements(By.tagName("td"));
        	dtoList.add(HitterDto.fromHitterInfo(info));
        });
        
        return dtoList;
    }

}
