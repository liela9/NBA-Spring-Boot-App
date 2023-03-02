package com.example.demo.views;

import com.example.demo.getgamesapi.Team;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;

public class InputForm extends FormLayout {
    TextField fullName = new TextField("Full name");
    EmailField email = new EmailField("Email");
    TextField fee = new TextField("Fee");
    TextField date = new TextField("Date");
    ComboBox<Team> homeTeam = new ComboBox<>("Home team");
    ComboBox<Team> visitorTeam = new ComboBox<>("Visitor team");


    Button save =  new Button("Save");
    Button delete =  new Button("Delete");
    Button cancel =  new Button("Cancel");

    public InputForm(List<Team> teams){
        addClassName("input-form");

        homeTeam.setItems(teams);
        homeTeam.setItemLabelGenerator(Team::getFullName);

        visitorTeam.setItems(teams);
        visitorTeam.setItemLabelGenerator(Team::getFullName);

        add(
                fullName,
                email,
                fee,
                date,
                homeTeam,
                visitorTeam,
                createBtnLayout()
        );
    }

    private Component createBtnLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, cancel);
    }
}
