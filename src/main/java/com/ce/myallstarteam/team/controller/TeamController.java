package com.ce.myallstarteam.team.controller;

import com.ce.myallstarteam.team.dto.request.TeamRequest;
import com.ce.myallstarteam.team.entity.Team;
import com.ce.myallstarteam.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v1/team")
public class TeamController {

    private final TeamService teamService;

    // temp
    @GetMapping
    public String showTeam(Model model) {
        return "team/index";
    }

    @GetMapping("/form")
    public String teamForm(Model model) {
        return "team/team-form";
    }

    @PostMapping
    public String createTeam(Model model, @ModelAttribute TeamRequest teamRequest) {

        teamService.createTeam(teamRequest);
        return "redirect:/api/v1/team/form";
    }
}
