package com.example.demo.input;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@ToString
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class MyInput {

    @Id
    @GeneratedValue
    @SerializedName("input_id")
    @Column(name = "input_id")
    private Long inputId;
    @Email
    @NotEmpty
    private String email = "";
    @NotEmpty
    private String date = "";
    @SerializedName("home_team")
    @Column(name = "home_team")
    @NotEmpty
    private String homeTeam = "";
    @SerializedName("visitor_team")
    @Column(name = "visitor_team")
    @NotEmpty
    private String visitorTeam = "";
    private double fee;

    public MyInput(String date, String homeTeam, String visitorTeam, double fee) {
        this.date = date;
        this.homeTeam = homeTeam;
        this.visitorTeam = visitorTeam;
        this.fee = fee;
    }

}
