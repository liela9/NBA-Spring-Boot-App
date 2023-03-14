package com.example.demo.views;

import com.example.demo.input.MyInput;
import com.example.demo.input.MyInputService;
import com.example.demo.security.SecurityService;
import com.example.demo.team.Team;
import com.example.demo.team.TeamService;
import com.example.demo.user.AppUser;
import com.example.demo.user.AppUserService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.context.annotation.Scope;

import javax.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Component
@Scope("prototype")
@PageTitle("My Inputs | NBA Tracker")
@Route(value = "my-inputs", layout = MainLayout.class)
@PermitAll
public class MyInputsView extends VerticalLayout {
    private MyInputService myInputService;
    private SecurityService securityService;
    private TeamService teamService;
    private AppUserService userService;
    private String userEmail = "";
    private InputForm form;
    Grid<MyInput> grid = new Grid<>(MyInput.class);
    TextField filterText = new TextField();
    TextField emailText = new TextField();


    public MyInputsView(MyInputService myInputService,
                        SecurityService securityService,
                        TeamService teamService,
                        AppUserService userService){
        this.myInputService = myInputService;
        this.securityService = securityService;
        this.teamService = teamService;
        this.userService = userService;

        addClassName("my-inputs-view");
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        configureGrid();
        configureForm();

        add(getToolbar(),
                getContent());

        updateList();
    }

    private void closeEditor() {
        form.setInput(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    //A grid of user inputs
    private void configureGrid(){
        grid.addClassName("my-inputs-grid");
        grid.setSizeFull();
        grid.setColumns("date", "homeTeam", "visitorTeam", "fee");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(e -> editInput(e.getValue()));

        try {
            AppUser user = userService.getUserByEmail(userEmail);
            grid.setItems(myInputService.getInputsByUser(user));
        }catch (IllegalStateException e){
            System.out.println("User does not exists");
        }
        //TODO: Test it
    }

    private void editInput(MyInput input) {
        if (input == null){
            closeEditor();
        }
        else {
            form.setInput(input);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void configureForm(){
        List<Team> teams = teamService.getAllTeams();
        List<String> teamsNames = new ArrayList<>();
        for (int i = 0; i < teams.size(); ++i) {
            teamsNames.add(teams.get(i).getFullName());
        }

        form = new InputForm(teamsNames);
        form.setWidth("25em");
        form.addListener(InputForm.SaveEvent.class, this::saveInput);
        form.addListener(InputForm.DeleteEvent.class, this::deleteInput);
        form.addListener(InputForm.CloseEvent.class, e -> closeEditor());
    }

    private void saveInput(InputForm.SaveEvent event) {
        try {
            myInputService.addNewInput(event.getInput());
        } catch (Exception e){}

        userEmail = event.getInput().getEmail();
        updateList();
        closeEditor();
    }

    private void deleteInput(InputForm.DeleteEvent event) {
        myInputService.deleteInput(event.getInput());
        updateList();
        closeEditor();
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Filter...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addInputBtn = new Button("Add input");
        addInputBtn.addClickListener(e -> addInput());

        emailText.setPlaceholder("Enter email to see your inputs");

        Button yourEmailBtn = new Button("Enter email");
        yourEmailBtn.addClickListener(e -> {
            userEmail = emailText.getValue();
            updateList();
        });

        HorizontalLayout toolbar = new HorizontalLayout(emailText, yourEmailBtn, filterText, addInputBtn);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addInput() {
        grid.asSingleSelect().clear();
        editInput(new MyInput());
    }

    //Content:  grid of user inputs
    //          form for adding new input
    private Component getContent(){
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.addClassName("content");
        content.setSizeFull();
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);

        return content;
    }

    private void updateList() {
        grid.setItems(myInputService.getAllInputs(userEmail, filterText.getValue()));
    }
}
