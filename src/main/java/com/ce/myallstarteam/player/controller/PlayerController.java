package com.ce.myallstarteam.player.controller;

import com.ce.myallstarteam.player.entity.Player;
import com.ce.myallstarteam.player.service.PlayerService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping
    ResponseEntity<Page<Player>> searchPlayers(
        @RequestParam("position") String position,
        @RequestParam("name") String name,
        @RequestParam("page") int page
    ){
        return new ResponseEntity<>(playerService.findPlayers(position, name, page), HttpStatus.OK);
    }

    @GetMapping("/{playerId}")
    ResponseEntity<Player> searchPlayer(@PathVariable int playerId){
        return new ResponseEntity<>(playerService.findPlayerDetail(playerId), HttpStatus.OK);
    }
}