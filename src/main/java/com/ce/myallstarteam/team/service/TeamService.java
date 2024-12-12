package com.ce.myallstarteam.team.service;

import com.ce.myallstarteam.team.dto.TeamDto;
import com.ce.myallstarteam.team.dto.TeamPlayerDto;
import com.ce.myallstarteam.team.entity.Team;
import com.ce.myallstarteam.team.repository.TeamRepository;
import com.ce.myallstarteam.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    public TeamService(TeamRepository teamRepository, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    public boolean isUserExists(int userId) {
        return userRepository.existsById(userId);
    }

    public List<TeamDto> getTeamsByUserId(int userId, String search) {
        List<Team> teams;
        if (search != null && !search.isEmpty()) {
            teams = teamRepository.findByUserIdAndNameContaining(userId, search);
        } else {
            teams = teamRepository.findByUserId(userId);
        }
        return teams.stream()
                .map(TeamDto::fromEntity)
                .collect(Collectors.toList());
    }

    public List<TeamPlayerDto> getPlayersByTeamId(int teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found"));
        return team.getTeamPlayers().stream()
                .map(TeamPlayerDto::fromEntity) // TeamPlayer 엔티티를 TeamPlayerDto로 변환
                .collect(Collectors.toList());
    }

    public boolean deleteTeam(int userId, int teamId) {
        return teamRepository.findById(teamId)
                .filter(team -> team.getUser().getId() == userId)
                .map(team -> {
                    teamRepository.delete(team);
                    return true;
                }).orElse(false);
    }

}
