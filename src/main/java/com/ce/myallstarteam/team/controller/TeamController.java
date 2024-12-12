package com.ce.myallstarteam.team.controller;

import com.ce.myallstarteam.team.dto.TeamDto;
import com.ce.myallstarteam.team.dto.TeamPlayerDto;
import com.ce.myallstarteam.team.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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

    @GetMapping("/team/form")
    public String createTeamForm() {
        return "team-create"; // team-create.html을 반환
    }

    @DeleteMapping("/team/{userId}/{teamId}")
    public String deleteTeamByUserIdAndTeamId(@PathVariable int userId, @PathVariable int teamId) {
        boolean deleted = teamService.deleteTeam(userId, teamId);
        if (deleted) {
            // 삭제 성공 시 팀 리스트 페이지로 리다이렉트
            return "redirect:/api/v1/team/" + userId;
        } else {
            // 삭제 실패 시 404 에러 처리
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found");
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
