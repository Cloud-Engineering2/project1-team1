package com.ce.myallstarteam.global.service;

import com.ce.myallstarteam.player.dto.DefenseDto;
import com.ce.myallstarteam.player.dto.HitterDto;
import com.ce.myallstarteam.player.dto.PitcherDto;
import com.ce.myallstarteam.player.entity.Player;
import com.ce.myallstarteam.player.entity.PlayerPosition;
import com.ce.myallstarteam.player.repository.PlayerRepository;
import com.ce.myallstarteam.team.entity.Team;
import com.ce.myallstarteam.team.entity.TeamPlayer;
import com.ce.myallstarteam.team.repository.TeamPlayerRepository;
import com.ce.myallstarteam.team.repository.TeamRepository;
import com.ce.myallstarteam.user.entity.User;
import com.ce.myallstarteam.user.repository.UserRepository;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DataService {
    private final PlayerRepository playerRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final TeamPlayerRepository teamPlayerRepository;
    private final PasswordEncoder passwordEncoder;

    public void dummyDataInit() {
        List<User> users = new ArrayList<>();
        for(int i = 1 ; i <= 500 ; i++){
            users.add(
                User.builder()
                    .username("user"+i)
                    .password(passwordEncoder.encode("pw"+i))
                    .email("user"+i+"@example.com")
                    .build()
            );
        }
        userRepository.saveAll(users);
        dummyTeamInit(users);
    }

    private void dummyTeamInit(List<User> users) {
        List<Team> teams = new ArrayList<>();
        for(User u : users){

            for(int i = 1; i<= 5 ; i++){
                teams.add(
                    Team.builder()
                        .user(u)
                        .name("T_"+u.getUsername()+"_"+i)
                        .build()
                );
            }
        }
        teamRepository.saveAll(teams);
        dummyTeamPlayerInit(teams);
    }

    private void dummyTeamPlayerInit(List<Team> teams) {
        Collection<Player> players = playerDataInit();
        List<Player> playerList = new ArrayList<>(players);
        List<TeamPlayer> teamPlayers = new ArrayList<>();
        PlayerPosition[] positions = PlayerPosition.values();

        for(Team t: teams){
            for(int i = 0 ; i < 9 ; i++){
                Random random = new Random();
                int randomId = random.nextInt(players.size());
                Player randomPlayer = playerList.get(randomId);

                teamPlayers.add(
                    TeamPlayer.builder()
                        .player(randomPlayer)
                        .team(t)
                        .position(positions[i].getPosition())
                        .build()
                );
            }
        }
        teamPlayerRepository.saveAll(teamPlayers);
    }

    public Collection<Player> playerDataInit() {
        WebDriver driver = new ChromeDriver();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            List<HitterDto> hitterDto = hitterInit(driver, wait);
            List<PitcherDto> pitcherDto = pitcherInit(driver, wait);
            List<DefenseDto> defenseDto = defenseInit(driver, wait);

            Map<String, Player> map = new HashMap<>();
            addHitter(hitterDto, map);
            addPitcher(pitcherDto, map);
            addDefense(defenseDto, map);

            playerRepository.saveAll(map.values());
            return map.values();
        }finally {
            driver.quit();
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
