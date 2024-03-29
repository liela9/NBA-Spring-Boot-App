package com.example.demo.team;

import com.example.demo.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("SELECT t FROM Team t WHERE t.fullName = ?1")
    Team findTeamByName(String fullName);

    @Query("SELECT t FROM Team t WHERE t.teamId = ?1")
    Team findTeamById(Long teamId);
}
