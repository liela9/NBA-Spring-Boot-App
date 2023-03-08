package com.example.demo.generator;

import com.example.demo.team.Team;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class GetGame {

    private Long id;
    private String date;
    @SerializedName("home_team")
    private Team homeTeam;
    @SerializedName("visitor_team")
    private Team visitorTeam;

}