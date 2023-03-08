package com.example.demo.input;

import com.example.demo.team.Team;
import com.example.demo.user.AppUser;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

//    @ManyToOne
//    @JoinColumn(name = "user_email", nullable = false)
//    @NotNull
//    private AppUser user;

    @Email
    @NotEmpty
    private String email = "";

    @NotEmpty
    private String date = "";

    @ManyToOne
    @JoinColumn(name = "home_team_id")
    @NotNull
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "visitor_team_id")
    @NotNull
    private Team visitorTeam;

    @NotNull
    private double fee;

    public MyInput(String date, Team homeTeam, Team visitorTeam, double fee) {
        this.date = date;
        this.homeTeam = homeTeam;
        this.visitorTeam = visitorTeam;
        this.fee = fee;
    }

}
