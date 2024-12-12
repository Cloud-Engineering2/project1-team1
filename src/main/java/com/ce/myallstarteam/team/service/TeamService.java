package com.ce.myallstarteam.team.service;

import com.ce.myallstarteam.player.entity.Player;
import com.ce.myallstarteam.player.repository.PlayerRepository;
import com.ce.myallstarteam.team.dto.TeamDto;
import com.ce.myallstarteam.team.dto.TeamPlayerDto;
import com.ce.myallstarteam.team.entity.Team;
import com.ce.myallstarteam.team.entity.TeamPlayer;
import com.ce.myallstarteam.team.repository.TeamPlayerRepository;
import com.ce.myallstarteam.team.repository.TeamRepository;
import com.ce.myallstarteam.user.dto.UserDto;
import com.ce.myallstarteam.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;
    private final TeamPlayerRepository teamPlayerRepository;

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

    @Transactional
    public void createTeam(TeamDto teamDto) {
        UserDto userDto = UserDto.fromEntity(
                userRepository.findById(teamDto.getUserId()).orElseThrow());

        Team savedTeam = teamRepository.save(teamDto.toEntity(userDto));

        List<TeamPlayer> teamPlayers = teamDto.getTeamPlayers().stream()
                .map(dto -> TeamPlayer.builder()
                        .team(savedTeam)
                        .player(playerRepository.findById(dto.getPlayerId()).orElseThrow())
                        .build())
                .toList();

        teamPlayerRepository.saveAll(teamPlayers);
    }
}
