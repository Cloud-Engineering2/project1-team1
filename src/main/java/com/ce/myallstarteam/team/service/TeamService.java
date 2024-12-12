package com.ce.myallstarteam.team.service;

import com.ce.myallstarteam.team.dto.request.TeamRequest;
import com.ce.myallstarteam.team.entity.Team;
import com.ce.myallstarteam.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public void createTeam(TeamRequest teamRequest) {


        return teamRepository.save(team);
    }
}
