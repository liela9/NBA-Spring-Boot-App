package com.example.demo.views;


import com.example.demo.game.Game;
import com.example.demo.game.GameService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import javax.annotation.security.PermitAll;


@PageTitle("All Games | NBA Tracker")
@Route(value = "", layout = MainLayout.class)
@PermitAll
public class AllGamesView extends VerticalLayout {
    private GameService gameService;
    Grid<Game> grid = new Grid<>(Game.class);
    TextField filterText = new TextField();

    public AllGamesView(GameService gameService){
        this.gameService = gameService;

        addClassName("all-games-view");
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        configureFilterText();
        configureGrid();

        add(filterText, grid);

        updateList();
    }

    private void configureGrid() {
        grid.setItems(gameService.getAllGames());
        grid.setColumns("date", "homeTeam", "visitorTeam");
    }

    private void configureFilterText() {
        filterText.setPlaceholder("Filter...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());
    }

    private void updateList() {
        grid.setItems(gameService.getAllGames(filterText.getValue()));
    }
}
