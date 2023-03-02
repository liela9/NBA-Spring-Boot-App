package com.example.demo.views;

import com.example.demo.game.Game;
import com.example.demo.input.MyInput;
import com.example.demo.input.MyInputService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.security.PermitAll;
import java.util.Collections;

@PageTitle("My Inputs | NBA Tracker")
@Route(value = "", layout = MainLayout.class)
@PermitAll
public class MyInputsView extends VerticalLayout {
    private MyInputService myInputService;
    Grid grid = new Grid<>(MyInput.class);
    InputForm form;

    public MyInputsView(){
        addClassName("my-inputs-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        getMyInputs();
        configureForm();

        //TODO: Add tool bar
        add(getContent());
    }

    private void getMyInputs(){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        grid.setItems(myInputService.getInputsByUser(username));
        //TODO: Test it
        grid.setColumns("date", "homeTeam", "visitorTeam", "fee", "user");

    }

    private void configureForm(){
        form = new InputForm(Collections.emptyList());
        form.setWidth("25em");
    }

    private Component getContent(){
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassName("content");
        content.setSizeFull();

        return content;
    }
}
