package com.example.demo.getgamesapi;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GetGame {

    private Integer id;
    private String date;
    @SerializedName("home_team")
    private Team homeTeam;
    @SerializedName("visitor_team")
    private Team visitorTeam;

}