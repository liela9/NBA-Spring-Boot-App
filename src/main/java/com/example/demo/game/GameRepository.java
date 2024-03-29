package com.example.demo.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("SELECT g FROM Game g WHERE g.gameId = ?1")
    Game findGameById(Long id);

    @Query("SELECT g FROM Game g WHERE g.date = ?1")
    List<Game> findGameByDate(String date);

    @Query("SELECT g FROM Game g WHERE g.homeTeam = ?1")
    List<Game> findGameByHomeTeam(String homeTeam);

    @Query("SELECT g FROM Game g WHERE g.visitorTeam = ?1")
    List<Game> findGameByVisitorTeam(String visitorTeam);

    @Query("select g from Game g " +
            "where lower(g.homeTeam) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(g.visitorTeam) like lower(concat('%', :searchTerm, '%'))" +
            "or lower(g.date) like lower(concat('%', :searchTerm, '%'))")
    List<Game> search(@Param("searchTerm") String searchTerm);
}