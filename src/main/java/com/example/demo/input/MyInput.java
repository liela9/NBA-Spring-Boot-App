package com.example.demo.input;

import com.example.demo.user.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;

@ToString
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class MyInput {
    @Id
    @SequenceGenerator(
            name = "input_sequence",
            sequenceName = "input_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "input_sequence"
    )
    @Column(name = "input_id")
    private Long inputId;

    @ManyToOne
    @JoinColumn(name = "user_email", nullable = false)
    @Email
    private AppUser user;

    @Column(name = "date")
    private String date;

    @Column(name = "home_team")
    private String homeTeam;

    @Column(name = "visitor_team")
    private String visitorTeam;

    @Column(name = "fee")
    private double fee;

    public MyInput(String date, String homeTeam, String visitorTeam, double fee) {
        this.date = date;
        this.homeTeam = homeTeam;
        this.visitorTeam = visitorTeam;
        this.fee = fee;
    }
}
