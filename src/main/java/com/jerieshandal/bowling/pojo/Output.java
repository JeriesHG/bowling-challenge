package com.jerieshandal.bowling.pojo;

import lombok.Data;

@Data
public class Output {

    private String playerName;
    private String firstRoll = " ";
    private String secondRoll = " ";
    private String bonusRoll = " ";
    private int frameScore;

    public Output(String playerName) {
        this.playerName = playerName;
    }
}
