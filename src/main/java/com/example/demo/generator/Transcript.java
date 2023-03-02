package com.example.demo.generator;

import lombok.Getter;

import java.util.List;


@Getter
public class Transcript {
    private List<GetGame> data;

    @Override
    public String toString() {
        String str = "Transcript=\n{" ;

        for(GetGame g: data) {
            str += g.toString() + "\n";
        }

        return str + "}";
    }
}