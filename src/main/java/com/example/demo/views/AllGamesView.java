package com.example.demo.views;


import com.example.demo.game.Game;
import com.example.demo.game.GameService;
import com.example.demo.getgamesapi.Transcript;
import com.example.demo.security.SecurityService;
import com.google.gson.Gson;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@PageTitle("All Games | NBA Tracker")
@Route(value = "all-games", layout = MainLayout.class)
@PermitAll
public class AllGamesView extends VerticalLayout {
    private GameService gameService;

    public AllGamesView(GameService gameService){
        this.gameService = gameService;
        addClassName("all-games-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        initDB();

        Grid grid = new Grid<>(Game.class);
        grid.setItems(gameService.getAllGames());
        grid.setColumns("date", "homeTeam", "visitorTeam");

        add(grid);

//        for (int i = 1; i < 10; i++) {
//            System.out.println(gameService.getGameById(Long.valueOf(i)));
//        }

    }

    private void initDB() {
        Gson gson = new Gson();
        Transcript getGamesTranscript = gson.fromJson(getGamesRequest(), Transcript.class);

        //extract values (=games) from the response
        for (int i = 0; i < getGamesTranscript.getData().size(); i++) {

            String date = getGamesTranscript.getData().get(i).getDate();
            String homeTeam = getGamesTranscript.getData().get(i).getHomeTeam().getFullName();
            String visitorTeam = getGamesTranscript.getData().get(i).getVisitorTeam().getFullName();

            //create new game and add it to the DB
            Game game = new Game(date, homeTeam, visitorTeam);
            gameService.addNewGame(game);
        }
    }

    private static String getGamesRequest(){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://free-nba.p.rapidapi.com/games?page=0&per_page=25"))
                .header("X-RapidAPI-Key", "a0c6c9533dmshf2ae46d0b2f2312p1ccad6jsnb72013b6f460")
                .header("X-RapidAPI-Host", "free-nba.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response.body();
    }
}
