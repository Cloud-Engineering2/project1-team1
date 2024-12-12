package com.ce.myallstarteam.team.controller;

import com.ce.myallstarteam.team.dto.TeamDto;
import com.ce.myallstarteam.team.dto.TeamPlayerDto;
import com.ce.myallstarteam.team.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/team/{userId}")
    public String getTeamsByUserId(
            @PathVariable int userId,
            String search,
            Model model) {
        if (!teamService.isUserExists(userId)) {
            throw new IllegalArgumentException("User not found.");
        }
        List<TeamDto> teams = teamService.getTeamsByUserId(userId, search);
        model.addAttribute("teams", teams);
        return "main"; // main.html로 연결
    }

    @GetMapping("/team/{userId}/{teamId}")
    public String getTeamDetails(
            @PathVariable int userId,
            @PathVariable int teamId,
            Model model) {
        List<TeamPlayerDto> players = teamService.getPlayersByTeamId(teamId);
        model.addAttribute("players", players);
        return "team-detail"; // team-detail.html로 연결
    }

    @GetMapping("/team/form")
    public String createTeamForm() {
        return "team-create"; // team-create.html을 반환
    }

    @PostMapping("/team")
    public String createTeam(@ModelAttribute TeamDto teamDto) {
        teamService.createTeam(teamDto);
        System.out.println(teamDto);
        return "redirect:/team/" + teamDto.getId(); // user id
    }

    @DeleteMapping("/team/{userId}/{teamId}")
    public ResponseEntity<Void> deleteTeamByUserIdAndTeamId(
            @PathVariable int userId,
            @PathVariable int teamId) {
        boolean deleted = teamService.deleteTeam(userId, teamId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 예외 처리 메서드
    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView handleIllegalArgumentException(IllegalArgumentException ex) {
        ModelAndView modelAndView = new ModelAndView("error"); // error.html로 이동
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

}
