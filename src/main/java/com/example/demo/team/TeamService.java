package com.example.demo.team;

import com.example.demo.game.Game;
import com.example.demo.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team getTeamById(Long id) {
        Team team = teamRepository.findTeamById(id);
        if(team == null)
            throw new IllegalStateException("team with id " + id + "does not exists");
        return team;
    }

    public Team getTeamByName(String name) {
        Team team = teamRepository.findTeamByName(name);
        if(team == null)
            throw new IllegalStateException("team " + name + " does not exists");
        return team;
    }

    public void addNewTeam(String name) {
        if(teamRepository.findTeamByName(name) == null)
            teamRepository.save(new Team(name));
    }

}
