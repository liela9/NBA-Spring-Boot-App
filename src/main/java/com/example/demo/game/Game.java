package com.example.demo.game;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@NoArgsConstructor
@Getter
@Entity
@Table
public class Game {
    @Id
    @GeneratedValue
    @Column(name = "game_id")
    private Long gameId;

    @Column(name = "date")
    private String date;

    @Column(name = "home_team")
    private String homeTeam;

    @Column(name = "visitor_team")
    private String visitorTeam;

    public Game(String date, String homeTeam, String visitorTeam) {
        this.date = date;
        this.homeTeam = homeTeam;
        this.visitorTeam = visitorTeam;
    }
}
