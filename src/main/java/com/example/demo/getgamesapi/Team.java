package com.example.demo.getgamesapi;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Team {

    private Integer id;
    @SerializedName("full_name")
    private String fullName;
}