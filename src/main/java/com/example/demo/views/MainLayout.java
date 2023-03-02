package com.example.demo.views;

import com.example.demo.security.SecurityService;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {
    private SecurityService securityService;

    public MainLayout(SecurityService securityService){
        this.securityService = securityService;

        createHeader();
        createDrawer();
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

        RouterLink allGamesView = new RouterLink("All Games", AllGamesView.class);
        allGamesView.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(myInputsView, allGamesView));
    }
}
