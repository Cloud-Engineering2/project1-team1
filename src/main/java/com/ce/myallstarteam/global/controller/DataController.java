package com.ce.myallstarteam.global.controller;

import com.ce.myallstarteam.global.service.DataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ce.myallstarteam.player.repository.PlayerRepository;
import com.ce.myallstarteam.player.service.PlayerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DataController {

    private final DataService dataService;

    @GetMapping("/data-init")
    ResponseEntity<Void> dataInit(){
        dataService.playerDataInit();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/dummy-data-init")
    ResponseEntity<Void> dummyDataInit(){
        dataService.dummyDataInit();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}