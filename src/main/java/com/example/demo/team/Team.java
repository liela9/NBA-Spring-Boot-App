package com.example.demo.team;

import com.example.demo.input.MyInput;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@ToString
@Entity
@Table
public class Team {

    @Id
    @GeneratedValue
    @SerializedName("team_id")
    @Column(name = "team_id")
    private Long teamId;

    @SerializedName("full_name")
    @Column(name = "full_name")
    private String fullName;

    @OneToMany(mappedBy = "homeTeam")
    @Nullable
    private List<MyInput> homeInputs;

    @OneToMany(mappedBy = "visitorTeam")
    @Nullable
    private List<MyInput> visitorInputs;

    public Team(String fullName){
        this.fullName = fullName;
    }
}