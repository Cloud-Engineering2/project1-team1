package com.ce.myallstarteam.player.service;

import com.ce.myallstarteam.player.entity.PlayerPosition;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ce.myallstarteam.player.dto.DefenseDto;
import com.ce.myallstarteam.player.dto.HitterDto;
import com.ce.myallstarteam.player.dto.PitcherDto;
import com.ce.myallstarteam.player.entity.Player;
import com.ce.myallstarteam.player.repository.PlayerRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;

    public Page<Player> findPlayers(String position, String name, int page) {
        Pageable pageable = PageRequest.of(page, 10);

        if(StringUtils.hasText(position) && StringUtils.hasText(name)){
            PlayerPosition.validateValue(position);
            return playerRepository.findByNameAndPosition(position, name, pageable);
        }else if(StringUtils.hasText(position)){
            PlayerPosition.validateValue(position);
            return playerRepository.findByPosition(position, pageable);
        }else if(StringUtils.hasText(name)){
            return playerRepository.findByName(name, pageable);
        }
        return playerRepository.findAll(pageable);
    }

    public Player findPlayerDetail(int playerId) {
        return playerRepository.findById(playerId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 선수입니다."));
    }
}