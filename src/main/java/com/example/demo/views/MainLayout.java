package com.example.demo.views;

import com.example.demo.game.Game;
import com.example.demo.game.GameService;
import com.example.demo.generator.Transcript;
import com.example.demo.security.SecurityService;
import com.example.demo.team.TeamService;
import com.google.gson.Gson;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MainLayout extends AppLayout {
    private SecurityService securityService;
    private GameService gameService;
    private TeamService teamService;

    public MainLayout(SecurityService securityService,
                      GameService gameService,
                      TeamService teamService){
        this.securityService = securityService;
        this.gameService = gameService;
        this.teamService = teamService;

        initDB();

        createHeader();
        createDrawer();
    }

    //initialize the data base
    private void initDB() {
        Gson gson = new Gson();
        Transcript getGamesTranscript = gson.fromJson(getGamesRequest(), Transcript.class);

        System.out.println(getGamesTranscript);

        //extract values (games and teams) from the response
        for (int i = 0; i < getGamesTranscript.getData().size(); i++) {

            String date = getGamesTranscript.getData().get(i).getDate();
            String homeTeam = getGamesTranscript.getData().get(i).getHomeTeam().getFullName();
            String visitorTeam = getGamesTranscript.getData().get(i).getVisitorTeam().getFullName();

            //create new game and add it to the DB
            gameService.addNewGame(new Game(date, homeTeam, visitorTeam));

            //create new team (if it does not exist) and add it to the DB
            teamService.addNewTeam(homeTeam);
            teamService.addNewTeam(visitorTeam);

            //TODO: Delete printing
//            System.out.println(gameService.getGameById(Long.valueOf(i)));
//            System.out.println(homeTeam);
//            System.out.println(visitorTeam);
//            System.out.println(date);
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

    private void createHeader() {
        Label logo = new Label("NBA Tracker");
        logo.addClassNames("text-l", "m-m");

        Button logoutBtn = new Button("Log out", e -> securityService.logout());

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logoutBtn);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames("py-0", "px-m");

        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink myInputsView = new RouterLink("My Inputs", MyInputsView.class);
        myInputsView.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(
                new VerticalLayout(
                    myInputsView,
                    new RouterLink("All Games", AllGamesView.class)));
    }
}
