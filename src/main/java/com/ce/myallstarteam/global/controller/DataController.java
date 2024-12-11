package com.ce.myallstarteam.global.controller;

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

	private final PlayerService playerService;
	
	@GetMapping("/data-init")
	ResponseEntity<Void> dataInit(){
		playerService.dataInit();
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
