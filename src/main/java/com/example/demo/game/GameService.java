package com.example.demo.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;
    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public List<Game> getAllGames(String filterText) {
        if (filterText == null || filterText.isEmpty())
            return gameRepository.findAll();

        return gameRepository.search(filterText);
    }
    public Game getGameById(Long gameId) {
        return gameRepository.findGameById(gameId);
    }
    public List<Game> getGameByHomeTeam(String homeTeam){
        return gameRepository.findGameByHomeTeam(homeTeam);
    }
    public void addNewGame(Game game) {
        gameRepository.save(game);
    }



}
