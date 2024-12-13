package com.ce.myallstarteam.team.controller;

import com.ce.myallstarteam.player.dto.PlayerDto;
import com.ce.myallstarteam.player.entity.Player;
import com.ce.myallstarteam.player.service.PlayerService;
import com.ce.myallstarteam.team.service.TeamPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/api/v1")
public class TeamPlayerController {

    private final TeamPlayerService teamPlayerService;
    private final PlayerService playerService;

    // 생성자 주입
    @Autowired
    public TeamPlayerController(TeamPlayerService teamPlayerService, PlayerService playerService) {
        this.teamPlayerService = teamPlayerService;
        this.playerService = playerService;
    }

    @GetMapping("/team/{userId}/{teamId}")
    public String getTeamDetails(@PathVariable int userId, @PathVariable int teamId, Model model) {
        Map<String, PlayerDto> positions = teamPlayerService.getTeamPlayersByTeamId(teamId);

        model.addAttribute("positions", positions);
        return "team-detail";
    }

    @GetMapping("/team/player/{playerId}")
    @ResponseBody
    public ResponseEntity<PlayerDto> getPlayerDetails(@PathVariable int playerId) {
        Player player = playerService.findPlayerDetail(playerId);
        PlayerDto playerDto = PlayerDto.fromEntity(player);
        return ResponseEntity.ok(playerDto);
    }

}